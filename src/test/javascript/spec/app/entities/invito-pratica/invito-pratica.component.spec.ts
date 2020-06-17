import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoPraticaComponent } from 'app/entities/invito-pratica/invito-pratica.component';
import { InvitoPraticaService } from 'app/entities/invito-pratica/invito-pratica.service';
import { InvitoPratica } from 'app/shared/model/invito-pratica.model';

describe('Component Tests', () => {
  describe('InvitoPratica Management Component', () => {
    let comp: InvitoPraticaComponent;
    let fixture: ComponentFixture<InvitoPraticaComponent>;
    let service: InvitoPraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoPraticaComponent],
      })
        .overrideTemplate(InvitoPraticaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitoPraticaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitoPraticaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InvitoPratica(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invitoPraticas && comp.invitoPraticas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
