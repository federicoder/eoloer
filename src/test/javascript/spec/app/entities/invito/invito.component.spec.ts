import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoComponent } from 'app/entities/invito/invito.component';
import { InvitoService } from 'app/entities/invito/invito.service';
import { Invito } from 'app/shared/model/invito.model';

describe('Component Tests', () => {
  describe('Invito Management Component', () => {
    let comp: InvitoComponent;
    let fixture: ComponentFixture<InvitoComponent>;
    let service: InvitoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoComponent],
      })
        .overrideTemplate(InvitoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Invito(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invitos && comp.invitos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
