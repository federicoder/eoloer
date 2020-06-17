import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { InvitatoComponent } from 'app/entities/invitato/invitato.component';
import { InvitatoService } from 'app/entities/invitato/invitato.service';
import { Invitato } from 'app/shared/model/invitato.model';

describe('Component Tests', () => {
  describe('Invitato Management Component', () => {
    let comp: InvitatoComponent;
    let fixture: ComponentFixture<InvitatoComponent>;
    let service: InvitatoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitatoComponent],
      })
        .overrideTemplate(InvitatoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitatoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitatoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Invitato(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invitatoes && comp.invitatoes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
