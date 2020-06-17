import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoPraticaUpdateComponent } from 'app/entities/invito-pratica/invito-pratica-update.component';
import { InvitoPraticaService } from 'app/entities/invito-pratica/invito-pratica.service';
import { InvitoPratica } from 'app/shared/model/invito-pratica.model';

describe('Component Tests', () => {
  describe('InvitoPratica Management Update Component', () => {
    let comp: InvitoPraticaUpdateComponent;
    let fixture: ComponentFixture<InvitoPraticaUpdateComponent>;
    let service: InvitoPraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoPraticaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InvitoPraticaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitoPraticaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitoPraticaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvitoPratica(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvitoPratica();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
